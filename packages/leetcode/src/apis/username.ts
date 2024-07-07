import { OpenAPIRoute, Str } from 'chanfana'
import { z } from 'zod'

import { usernameBadge } from '../services/username'

export class Username extends OpenAPIRoute {
  schema = {
    tags: ['LeetCode'],
    request: {
      params: z.object({
        username: Str({
          required: true,
        }),
      }),
    },
    responses: {
      200: {
        description: 'Successful Response',
        content: {
          'img/svg+xml': {
            schema: Str(),
          },
        },
      },
    },
  }

  async handle() {
    const data = await this.getValidatedData<typeof this.schema>()
    const { username } = data.params
    const shield = await usernameBadge(username)
    return new Response(shield, {
      headers: {
        'content-type': 'image/svg+xml',
      },
    })
  }
}
